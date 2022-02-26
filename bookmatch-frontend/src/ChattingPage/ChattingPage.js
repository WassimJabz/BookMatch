import './ChattingPage.css';
import { useState } from 'react';

export default function ChattingPage(){

    const [users, setUsers] = useState([
        { username: 'Wassim', picUrl: 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png' },
        { username: 'Enzo', picUrl: 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png' },
        { username: 'Adam', picUrl: 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png' },
        { username: 'Philippe', picUrl: 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png' },
    ]);

    const [user, setUser] = useState(null);

    return (
        <div>
            <div className="selection-container">
                <UserSelection users={users} selectUser={(u) => setUser(u)}/>
            </div>
            <div className="chat-container">
                <Chat user={user} />
            </div>
        </div>
    )
}

const UserSelection = ({ users, selectUser }) => {
    return(
        <div>
            {users.map(u =>
                <div onClick={() => selectUser(u)} className="selection" key={u.username}>
                    <img src={u.picUrl} />
                    <div>{u.username}</div>
                </div>
            )}
        </div>
    )
}

const Chat = ({ user }) => {

    const[messages, setMessages] = useState([
        { sent: true, msg: 'hello '},
        { sent: true, msg: 'How are u?' },
        { sent: false, msg: "I'm fine and you" }
    ]);

    function addMessage(sent, msg){
        setMessages(() => 
            messages.push({ sent, msg })
        )
    }

    return (
        <div>
            {user ?
                <h1>{user.username}</h1>
            :
                <div>Select a user to chat</div>
            }
            <div className="chat-box">
                {
                    messages.map((m, i) => 
                        <div key={`msg: ${i}`} class={`row ${m.sent ? 'sent' : 'received'}`}>
                            <div className="message">{m.msg}</div>
                        </div>
                    )
                }
            </div>
            <div className="entry">
                <input type="text" placeholder="Type message..." />
                <button>Send</button>
            </div>
        </div>
    )
}