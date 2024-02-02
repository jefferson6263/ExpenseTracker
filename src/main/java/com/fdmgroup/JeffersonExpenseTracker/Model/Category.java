package com.fdmgroup.JeffersonExpenseTracker.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private int id;
	private String name;

	@ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Expense> expenses = new ArrayList<Expense>();
	
	
	public Category(String name) {
		super();
		this.name = name;
	}

	public Category() {
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

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Expense expense) {
		this.expenses.add(expense);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", expenses=" + expenses + "]";
	}

}
