// --- INITIAL SETUP & SECURITY ---
const userId = localStorage.getItem('userId');
const isLoggedIn = localStorage.getItem('isLoggedIn');

// If the user isn't logged in, kick them back to the login page
if (isLoggedIn !== 'true' || !userId) {
    window.location.href = 'login.html';
}

const todoInput = document.getElementById('todo-input');
const todoList = document.getElementById('todo-list');

// --- ADD NEW TASK (POST) ---
async function addTask() {
  const title = todoInput.value.trim();
  if (!title) return; // Don't add empty tasks

  try {
    const response = await fetch(`http://localhost:8080/api/todos/${userId}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title: title })
    });

    if (response.ok) {
      const newTask = await response.json();
      renderTask(newTask); // Add to UI immediately
      todoInput.value = ''; // Clear input field
    } else {
      alert("Error saving task to server.");
    }
  } catch (error) {
    console.error("Error adding task:", error);
  }
}

// --- LOAD TASKS (GET) ---
async function loadTodos() {
  try {
    const response = await fetch(`http://localhost:8080/api/todos/${userId}`);
    if (!response.ok) throw new Error("Failed to fetch tasks");
    
    const tasks = await response.json();
    todoList.innerHTML = ''; // Clear the list before rendering
    tasks.forEach(task => renderTask(task));
  } catch (error) {
    console.error("Error loading tasks:", error);
  }
}

// --- EDIT TASK (PUT) ---
async function editTask(id, currentTitle) {
  const newTitle = prompt("Edit your task:", currentTitle);
  if (newTitle === null || newTitle.trim() === "" || newTitle === currentTitle) return;

  try {
    const response = await fetch(`http://localhost:8080/api/todos/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title: newTitle })
    });

    if (response.ok) {
      loadTodos(); // Refresh the list to show updates
    }
  } catch (error) {
    console.error("Error updating task:", error);
  }
}

// --- DELETE TASK (DELETE) ---
async function deleteTask(id, buttonElement) {
  try {
    const response = await fetch(`http://localhost:8080/api/todos/${id}`, {
      method: 'DELETE'
    });

    if (response.ok) {
      // Remove the <li> element from the DOM
      buttonElement.closest('li').remove();
    }
  } catch (error) {
    console.error("Error deleting task:", error);
  }
}

// --- UI RENDERING ---
function renderTask(task) {
  const li = document.createElement('li');
  li.className = 'todo-item';
  li.innerHTML = `
    <span>${task.title}</span>
    <div class="actions">
        <button class="edit-btn" onclick="editTask(${task.id}, '${task.title}')">Edit</button>
        <button class="delete-btn" onclick="deleteTask(${task.id}, this)">Delete</button>
    </div>
  `;
  todoList.appendChild(li);
}

// --- LOGOUT ---
function logout() {
  localStorage.clear();
  window.location.href = 'login.html';
}

// Start the app by loading the user's data
loadTodos();