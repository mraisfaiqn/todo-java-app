// --- 1. INITIAL SETUP & SECURITY ---
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userDisplay');
const isLoggedIn = localStorage.getItem('isLoggedIn');

// Redirect if not logged in
if (isLoggedIn !== 'true' || !userId) {
  window.location.href = 'login.html';
}

const nameDisplay = document.getElementById('username-display');
nameDisplay.innerText = userName;

const todoInput = document.getElementById('todo-input');
const todoList = document.getElementById('todo-list');

// --- 2. LOAD TASKS (GET) ---
async function loadTodos() {
  try {
    const response = await fetch(`http://localhost:8080/api/todos/${userId}`);
    if (!response.ok) throw new Error("Failed to fetch tasks");
    
    const tasks = await response.json();
    todoList.innerHTML = ''; 
    tasks.forEach(task => renderTask(task));
  } catch (error) {
    console.error("Error loading tasks:", error);
  }
}

// --- 3. ADD NEW TASK (POST) ---
async function addTask() {
  const title = todoInput.value.trim();
  if (!title) return;

  try {
    const response = await fetch(`http://localhost:8080/api/todos/${userId}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title: title })
    });

    if (response.ok) {
      const newTask = await response.json();
      renderTask(newTask); 
      todoInput.value = ''; 
    }
  } catch (error) {
    console.error("Error adding task:", error);
  }
}

// --- 4. EDIT UI HANDLER (Toggle visibility) ---
function editHandler(id) {
  const titleElement = document.getElementById("title" + id);
  const editBtn = document.getElementById("edit" + id);
  const inputField = document.getElementById("input" + id);
  const doneBtn = document.getElementById("done" + id);

  // Hide display mode
  titleElement.setAttribute("hidden", true);
  editBtn.setAttribute("hidden", true);
  
  // Show edit mode
  inputField.removeAttribute("hidden");
  doneBtn.removeAttribute("hidden");
  
  inputField.focus();

  // Add "Enter" key listener for this specific input
  inputField.addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        saveEdit(id);
    }
  });
}

// --- 5. SAVE EDIT (PUT) ---
async function saveEdit(id) {
  const newTitle = document.getElementById("input" + id).value.trim();
  if (!newTitle) return; // Prevent saving empty titles

  try {
    const response = await fetch(`http://localhost:8080/api/todos/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title: newTitle })
    });

    if (response.ok) {
      loadTodos(); // Refresh to show the updated title
    }
  } catch (error) {
    console.error("Error updating task:", error);
  }
}

// --- 6. DELETE TASK (Triggered by Checkbox) ---
async function deleteTask(id) {
  try {
    const response = await fetch(`http://localhost:8080/api/todos/${id}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        loadTodos(); 
    }
  } catch (error) {
    console.error("Error deleting task:", error);
  }
}

// --- 7. UI RENDERING ---
function renderTask(task) {
  const itemDiv = document.createElement('div');
  itemDiv.className = 'item';
  
  itemDiv.innerHTML = `
    <input type="checkbox" onchange="deleteTask(${task.id})">
    
    <p id="title${task.id}">${task.title}</p>
    
    <input id="input${task.id}" type="text" value="${task.title}" class="edit-input" hidden />
    
    <div class="actions">
      <button id="edit${task.id}" class="edit-btn" onclick="editHandler(${task.id})">Edit</button>
      <button id="done${task.id}" class="edit-btn" onclick="saveEdit(${task.id})" hidden>Save</button>
    </div>
  `;
  todoList.appendChild(itemDiv);
}

// --- 8. LOGOUT ---
function logout() {
  localStorage.clear();
  window.location.href = 'login.html';
}

// Global listener for the main "Add" input (Enter key)
todoInput.addEventListener("keypress", (e) => {
  if (e.key === "Enter") addTask();
});

// Run load on startup
loadTodos();