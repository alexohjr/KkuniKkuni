package bean.orderlist;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderlistDataBean {
	private int orderNo; // order_No :: �ֹ���ȣ
	private Timestamp orderDate; // orderDate :: �ֹ���¥
	private Date rentalD1; // rental_D1 :: �뿩�Ⱓ(����)
	private Date rentalD2; // rental_D2 :: �뿩�Ⱓ(����)
	private int orderAmount; // order_amount :: �ֹ�����
	private int payTotal; // pay_total :: �����ݾ�
	private String payMethod; // pay_method :: ��������
	private String orderState; // order_state :: ����(�ֹ�����)
	private String parcelNum; // parcel_Num �����ȣ
	private String orderMeg;
	private String itemNo;
	private String mId;
	private String eId;
	private String mName;
	private String itemName;
	private String eName;

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int i) {
		this.orderNo = i;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRentalD1() {
		return rentalD1;
	}

	public void setRentalD1(Date date) {
		this.rentalD1 = date;
	}

	public Date getRentalD2() {
		return rentalD2;
	}

	public void setRentalD2(Date rentalD2) {
		this.rentalD2 = rentalD2;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(int payTotal) {
		this.payTotal = payTotal;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getParcelNum() {
		return parcelNum;
	}

	public void setParcelNum(String parcelNum) {
		this.parcelNum = parcelNum;
	}

	public String getOrderMeg() {
		return orderMeg;
	}

	public void setOrderMeg(String orderMeg) {
		this.orderMeg = orderMeg;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getitemName() {
		return itemName;
	}

	public void setitemName(String itemName) {
		this.itemName = itemName;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}
}
