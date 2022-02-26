import { useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import './LoginPage.css'

export default function LoginPage() {

    const [login, setLogin] = useState(true);

    return (
    <>
    <div className="container">
        <div>
            <div class="login-or-signup">
                <h1 className={login ? "" : "not-selected"} onClick={() => setLogin(true)}>Login</h1>
                <h1 className={login ? "not-selected" : ""} onClick={() => setLogin(false)}>Sign up</h1>
            </div>
            <div className="form">
                {login ? <></> : 
                <div class="field">
                    <label>Email:</label>
                    <input type='text' placeholder='Email'></input>
                </div>
                }
                <div class="field">
                    <label>Username:</label>
                    <input type='text' placeholder='Username'></input>
                </div>
                <div class="field">
                    <label>Password:</label>
                    <input type='password' placeholder='Password'></input>
                </div>
                {login ? <></> : 
                <div class="field">
                    <label>Confirm Password:</label>
                    <input type='password' placeholder='Confirm password'></input>
                </div>
                }
                <button>{login ? 'Login' : 'Sign up'}</button>
            </div>
        </div>
    </div>
    </>
    );
  }