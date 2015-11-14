package com.bordeaux.entity.pert;

class Node {

	private int num;
	private float tot;
	private float tard;

	public Node(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getTard() {
		return tard;
	}

	public float getTot() {
		return tot;
	}

	public void setTard(float tard) {
		if (this.tard > tard || this.tard == 0) {
			this.tard = tard;
		}
	}

	public void initTard() {
		this.tard = tot;
	}

	public void setTot(float tot) {
		if (this.tot < tot) {
			this.tot = tot;
		}
	}

}
