export default function LoginPage() {
    return (
    <>
    <div style={{display: 'flex', justifyContent: 'center'}}>
    <div>
        <h1>Login</h1> 
            <div className="form">
            <label>Username:</label>
            <input type='text' placeholder='Username' id='usernameInput'></input>
            <label>Password:</label>
            <input type='text' placeholder='Password' id='passwordInput'></input>
            <button id='loginButton'>Login</button>
        </div>
    </div>
    </div>
    </>
    );
  }