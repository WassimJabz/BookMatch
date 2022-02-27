import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import { login, register } from '../account';
import './LoginPage.css'

export default function LoginPage() {

    const [isLogin, setLogin] = useState(true);

    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [passwordConf, setPasswordConf] = useState('');

    function submit(){
        if(isLogin){
            login(username, password);
        } else {
            if(password == passwordConf)
                register(email, username, password);
        }
    }

    return (
    <>
    <div className="container">
        <div>
            <div class="login-or-signup">
                <h1 className={isLogin ? "" : "not-selected"} onClick={() => setLogin(true)}>Login</h1>
                <h1 className={isLogin ? "not-selected" : ""} onClick={() => setLogin(false)}>Sign up</h1>
            </div>
            <div className="form">
                {isLogin ? <></> : 
                <div class="form-field">
                    <label>Email:</label>
                    <input type='text' placeholder='Email' value={email} onChange={(e) => setEmail(e.target.value)}></input>
                </div>
                }
                <div class="form-field">
                    <label>Username:</label>
                    <input type='text' placeholder='Username'value={username} onChange={(e) => setUsername(e.target.value)}></input>
                </div>
                <div class="form-field">
                    <label>Password:</label>
                    <input type='password' placeholder='Password' value={password} onChange={(e) => setPassword(e.target.value)}></input>
                </div>
                {isLogin ? <></> : 
                <div class="form-field">
                    <label>Confirm Password:</label>
                    <input type='password' placeholder='Confirm password' value={passwordConf} onChange={(e) => setPasswordConf(e.target.value)}></input>
                </div>
                }
                <button onClick={() => submit()}>{isLogin ? 'Login' : 'Sign up'}</button>
            </div>
        </div>
    </div>
    </>
    );
  }