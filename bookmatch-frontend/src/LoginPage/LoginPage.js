import './LoginPage.css'

export default function LoginPage() {
    return (
    <>
    <div className="container">
        <div>
            <h1>Login</h1> 
            <div className="form">
                <div class="field">
                    <label>Username:</label>
                    <input type='text' placeholder='Username' id='usernameInput'></input>
                </div>
                <div class="field">
                    <label>Password:</label>
                    <input type='text' placeholder='Password' id='passwordInput'></input>
                </div>
                <button id='loginButton'>Login</button>
            </div>
        </div>
    </div>
    </>
    );
  }