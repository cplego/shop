package org.cplego.foodie.service.impl;

import org.cplego.foodie.enums.OrderStatusEnum;
import org.cplego.foodie.enums.YseOrNo;
import org.cplego.foodie.mapper.OrderItemsMapper;
import org.cplego.foodie.mapper.OrderStatusMapper;
import org.cplego.foodie.mapper.OrdersMapper;
import org.cplego.foodie.pojo.*;
import org.cplego.foodie.pojo.bo.OrderBO;
import org.cplego.foodie.pojo.vo.OrderVO;
import org.cplego.foodie.service.AddressService;
import org.cplego.foodie.service.ItemService;
import org.cplego.foodie.service.OrderService;
import org.cplego.foodie.utils.JSONResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl extends BaseService implements OrderService {
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderItemsMapper orderItemsMapper;
	@Autowired
	private OrderStatusMapper orderStatusMapper;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private Sid sid;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public OrderVO createOrder(OrderBO orderBO) throws Exception {
		Integer postAmount = 0; //邮费默认0
		String userId =  orderBO.getUserId();
		String addressId = orderBO.getAddressId();
		String msg = orderBO.getLeftMsg();
		String itemSpecs = orderBO.getItemSpecIds();
		Integer payMethod = orderBO.getPayMethod();
		//1.订单表
		String orderId = sid.nextShort();
		Orders orders = new Orders();
		orders.setId(orderId);
		orders.setLeftMsg(msg); //买家留言
		orders.setPayMethod(payMethod); //支付方式
		orders.setCreatedTime(new Date());
		orders.setUpdatedTime(new Date());
		orders.setUserId(userId);

		UserAddress userAddress = addressService.queryUserAddres(addressId);
		orders.setReceiverName(userAddress.getReceiver()); //商品接收人
		orders.setReceiverMobile(userAddress.getMobile());//商品收货人手机号
		orders.setReceiverAddress(userAddress.getReceiver()+userAddress.getCity()
									+userAddress.getDistrict()+userAddress.getDetail()); //收货地址

		// orders.setRealPayAmount(); //订单实际支付总价格  各类商品购买数*优惠价格 + 邮费
		// orders.setTotalAmount(); //订单商品总价格
		orders.setPostAmount(postAmount); // 邮费
		orders.setIsComment(YseOrNo.N.type);
		orders.setIsDelete(YseOrNo.N.type);

		//2 .子订单关联表
		Integer toallNomalAmount = 0; //商品累计价格
		Integer toallDiscountAmount = 0; //商品优惠累计价格

		String[] itemSpecArr = itemSpecs.split(",");
		for(String itemSpec : itemSpecArr){
			// TODO 整合redis 后购买数量从redis 购物车中获取
			OrderItems orderItems = new OrderItems();
			Integer buyCount = 1;

			ItemsSpec itemsSpec = itemService.queryItemSpec(itemSpec);
			toallNomalAmount += itemsSpec.getPriceNormal();
			toallDiscountAmount += itemsSpec.getPriceDiscount();
			orderItems.setId(sid.nextShort());
			orderItems.setOrderId(orderId); //关联订单id
			orderItems.setItemId(itemsSpec.getItemId());
			orderItems.setBuyCounts(buyCount); // 购买数量
			orderItems.setItemSpecId(itemSpec);
			orderItems.setItemSpecName(itemsSpec.getName());
			orderItems.setPrice(itemsSpec.getPriceDiscount()); // 成交价格

			ItemsImg itemsImg = itemService.queryItemImg(itemsSpec.getItemId());
			orderItems.setItemImg(itemsImg.getUrl());

			Items items = itemService.queryItemById(itemsSpec.getItemId());
			orderItems.setItemName(items.getItemName());
			//2.1 关联子订单表插入
			orderItemsMapper.insert(orderItems);

			//2.2 减少商品对应规格的库存
			itemService.descItemsSpecStock(itemSpec,buyCount);

		}
		/* orders.setRealPayAmount(toallDiscountAmount); //订单实际支付总价格  各类商品购买数*优惠价格 + 邮费
		 orders.setTotalAmount(toallNomalAmount); //订单商品总价格*/
		orders.setRealPayAmount(1); //订单实际支付总价格  各类商品购买数*优惠价格 + 邮费
		orders.setTotalAmount(1); //订单商品总价格*/
		//3订单状态表
		OrderStatus orderStatus  = new OrderStatus();
		orderStatus.setCreatedTime(new Date());
		orderStatus.setOrderId(orderId);
		orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
		orderStatusMapper.insert(orderStatus);

		ordersMapper.insert(orders);
		OrderVO orderVO = new OrderVO();
		orderVO.setOrderBO(orderBO);
		//orderVO.setAmount(toallDiscountAmount);
		orderVO.setAmount(1);
		orderVO.setMerchantOrderId(orderId);
		orderVO.setReturnUrl(recallUrl);
		return orderVO;

	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateOrderStatus(String orderId, int orderStatus) {
		OrderStatus oStatus  = new OrderStatus();
		oStatus.setOrderId(orderId);
		oStatus.setOrderStatus(orderStatus);
		oStatus.setPayTime(new Date());
		orderStatusMapper.updateByPrimaryKeySelective(oStatus);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public OrderStatus queryOrderStatus(String orderId) {
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrderId(orderId);
		OrderStatus result =  orderStatusMapper.selectByPrimaryKey(orderStatus);
		return result;
	}
}
