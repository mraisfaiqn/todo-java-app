// --- 1. REGISTRATION LOGIC ---
const registerForm = document.getElementById('register-form');

if (registerForm) {
  registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const username = document.getElementById('reg-username').value;
    const password = document.getElementById('reg-password').value;
    const messageDiv = document.getElementById('message');

    try {
      const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      });

      const result = await response.text();
      messageDiv.innerText = result;
      messageDiv.style.color = response.ok ? "green" : "red";

      if (response.ok) {
        setTimeout(() => {
          window.location.href = 'login.html';
        }, 1500);
      }
    } catch (error) {
      messageDiv.innerText = "Error: Could not connect to the server.";
    }
  });
}

// --- 2. LOGIN LOGIC ---
const loginForm = document.getElementById('login-form');

if (loginForm) {
  loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    // Ensure these IDs match your login.html inputs
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;
    const messageDiv = document.getElementById('message');

    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      });

      if (response.ok) {
        const data = await response.json(); 
        
        // Storing session data
        localStorage.setItem('userDisplay', username);
        localStorage.setItem('isLoggedIn', 'true');
        // Note: Check if your backend returns 'id' or 'userId'
        localStorage.setItem('userId', data.id || data.userId); 
        localStorage.setItem('username', data.username);
        
        window.location.href = 'index.html';
      } else {
        const errorText = await response.text();
        messageDiv.innerText = errorText;
        messageDiv.style.color = "red";
      }
    } catch (error) {
      messageDiv.innerText = "Error: Backend not responding.";
    }
  });
}

// --- 3. UTILITY FUNCTIONS ---
function logout() {
  localStorage.clear();
  window.location.href = 'login.html';
}