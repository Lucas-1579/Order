package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {

	private SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");

	private Date moment;
	private OrderStatus orderStatus;

	private List<OrderItem> orderitems = new ArrayList<>();
	private Client client;

	public Order() {
	}

	public Order(Client client, OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderItem> getOrderitems() {
		return orderitems;
	}

	public void addItem(OrderItem item) {
		orderitems.add(item);
	}

	public void removeItem(OrderItem item) {
		orderitems.remove(item);
	}

	public Double total() {
		double sum = 0.0;
		for (OrderItem o : orderitems) {
			sum += o.subTotal();
		}
		return sum;
	}

	public String toString() {
		Date dt = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append("ORDER SUMMARY: \n");
		sb.append("Order moment: " + "(" + sdf2.format(dt) + ")" + "\n");
		sb.append("Order status: " + orderStatus + "\n");
		sb.append("Client: " + client.getName() + " " + "(" + sdf3.format(client.getBirthDate()) + ")" + " - " + client.getEmail() + "\n");
		sb.append("Order items: \n");
		for (OrderItem i : orderitems) {
			sb.append(i.getProduct().getName() + ", " + "$" + i.getProduct().getPrice() + ", Quantity: " + i.getQuantity()
					+ ", Subtotal: " + "$" + String.format("%.2f", i.subTotal()) + "\n");
		}
		sb.append("Total price: " + "$" + String.format("%.2f", total()));
		return sb.toString();
	}

}
