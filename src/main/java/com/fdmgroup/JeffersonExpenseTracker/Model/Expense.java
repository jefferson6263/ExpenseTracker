package com.fdmgroup.JeffersonExpenseTracker.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Expense {

	@Id
	@GeneratedValue
	@Column(name = "expense_id")
	private int id;

	private String name;
	private double amount;
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "start_date")
	private Timestamp startDate;

	@Column(name = "end_name")
	private Timestamp endDate;

	@ManyToMany
	@JoinTable(name = "categorisation", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "expense_id"))
	private List<Category> categories = new ArrayList<Category>();

	public Expense(String name, double amount, String description, Timestamp startDate, Timestamp endDate) {
		super();
		this.name = name;
		this.amount = amount;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Expense() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(Category categories) {
		this.categories.add(categories);
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", amount=" + amount + ", Description=" + description
				+ ", user=" + user + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}