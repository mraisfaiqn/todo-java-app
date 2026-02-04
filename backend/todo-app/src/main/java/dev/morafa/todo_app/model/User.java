package dev.morafa.todo_app.model;

public class User {
	private String username;
	private String password;

	// 1. Default Constructor (Required for JSON mapping) ⚙️
	public User() {
	}

	// 2. Parameterized Constructor (For manual creation) 👤
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// 3. Getters (Required so AuthService and Jackson can read the data) 🔑
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	// 4. Setters (Required so Jackson can "fill in" the data from the web) 📥
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}