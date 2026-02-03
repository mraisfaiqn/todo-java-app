// 1. THE BOUNCER: Check if the user is logged in immediately
if (localStorage.getItem('isLoggedIn') !== 'true') {
    window.location.href = 'login.html'; // Kick them back to login if no flag is found
}

// 2. LOGOUT LOGIC: A way to clear the "notebook" 
function logout() {
    localStorage.removeItem('isLoggedIn'); // Remove the flag
    window.location.href = 'login.html';   // Send them back to the start
}

// Ensure the script runs only after the HTML is fully loaded
document.addEventListener('DOMContentLoaded', () => {
  const API_URL = "http://localhost:8080/api/todos";
  const todoList = document.getElementById('todoList');
  const todoInput = document.getElementById('todoInput');
  const addBtn = document.getElementById('addBtn');

  // 1. Add Task (Create)
  addBtn.onclick = async () => {
    const title = todoInput.value.trim();
    
    if (title === "") {
      alert("Please enter a task!");
      return;
    }

    await fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title: title }) 
    });

    todoInput.value = ''; // Clear the input
    fetchTodos(); // Refresh the list
  };

  // 2. Fetch and Display (Read)
  async function fetchTodos() {
    try {
      const response = await fetch(API_URL);
      const todos = await response.json();
      
      todoList.innerHTML = '';
      todos.forEach(todo => {
        // Create a div with the 'item' class from your CSS
        const itemDiv = document.createElement('div');
        itemDiv.className = 'item';

        // Checkbox for deleting (matching your CSS input[type="checkbox"])
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.onclick = () => deleteTodo(todo.id);

        // Paragraph for the text
        const p = document.createElement('p');
        p.textContent = todo.title;

        // Edit button for updating field
        const editIcon = document.createElement('button');
        editIcon.textContent = 'Edit'
        editIcon.className = 'edit';
        editIcon.onclick = () => editTodo(todo.id, todo.title);

        itemDiv.appendChild(checkbox);
        itemDiv.appendChild(p);
        itemDiv.appendChild(editIcon);
        todoList.appendChild(itemDiv);
      });
    } catch (error) {
      console.error("Error fetching todos:", error);
    }
  }

  // 3. Edit Task (Update)
  async function editTodo(id, currentTitle) {
    const newTitle = prompt("Edit your task:", currentTitle);
    
    // If the user didn't cancel and the title isn't empty
    if (newTitle !== null && newTitle.trim() !== "") {
      await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: newTitle.trim() }) 
      });
      fetchTodos(); // Refresh the UI
    }
  }

  // 4. Delete Task (Delete)
  async function deleteTodo(id) {
    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    fetchTodos();
  }

  // Initial load when the page opens
  fetchTodos();
});