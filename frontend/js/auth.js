// 1. Grab the registration form from the HTML
const registerForm = document.getElementById('register-form');

if (registerForm) {
  registerForm.addEventListener('submit', async (e) => {
    // Prevent the page from reloading
    e.preventDefault();

    // 2. Get the values from the input fields
    const username = document.getElementById('reg-username').value;
    const password = document.getElementById('reg-password').value;
    const messageDiv = document.getElementById('message');

    // 3. Send the data to your Java backend using fetch
    try {
      const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // The keys here must match your User.java fields!
        body: JSON.stringify({ username, password })
      });

      const result = await response.text();
      messageDiv.innerText = result; // Display "Registration successful!" or error
      
      if (result.includes("successful")) {
        setTimeout(() => {
          window.location.href = 'login.html';
        }, 1000);
      }
    } catch (error) {
      messageDiv.innerText = "Error: Could not connect to the server.";
    }
  });
}

const loginForm = document.getElementById('login-form');

if (loginForm) {
  loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;
    const messageDiv = document.getElementById('message');

    try {
      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      });

      // Check if the backend gave us the green light 🚦
      if (response.ok) {
        const data = await response.json(); // Convert response to JSON object
        
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('userId', data.userId); // Save the ID for later!
        
        window.location.href = 'index.html';
      } else {
        const errorText = await response.text();
        messageDiv.innerText = errorText;
      }
    } catch (error) {
      messageDiv.innerText = "Error: Backend not responding.";
    }
  });
}