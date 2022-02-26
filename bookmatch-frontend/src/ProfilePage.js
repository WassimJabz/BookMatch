import { useState } from "react"
import './ProfilePage.css'

export default function ProfilePage(){

    const [picUrl, setPicUrl] = useState('https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png');
    const [username, setUsername] = useState('username');
    const [email, setEmail] = useState('email@mail.com');
    const [book1, setBook1] = useState({
        num: 1,
        isbn: '2435341235412',
        title: 'The best book ever',
        author: 'Philippe Sarouphim Hochar'
    });
    const [book2, setBook2] = useState(null);
    const [book3, setBook3] = useState(null);

    return(
        <>
            <div className="header">
                <img src={picUrl} />
                <h1>{username}</h1>
            </div>
            <section>
                <h2>Your info</h2>
                <EditableInput title="Email:" value={email} />
                <EditableInput title="Username:" value={username} onChange={(e) => setUsername(e.target.value)}/>
                <EditableInput title="Profile pic url:" value={picUrl} onChange={(e) => setPicUrl(e.target.value)}/>
            </section>
            <section>
                <h2>Your favourites</h2>
                <BookSelection num={1} book={book1} setBook={(b) => setBook1(b)}/>
                <BookSelection num={2} book={book2} setBook={(b) => setBook2(b)}/>
                <BookSelection num={3} book={book3} setBook={(b) => setBook3(b)}/>
            </section>
        </>
    )
}

const EditableInput = ({ title, value, onChange }) => {

    const [editMode, setEditMode] = useState(false);

    return (
        <div className="edit">
            <label>{title}</label>
            <div className="field">
                {editMode ?
                    <input type="text" value={value} onChange={(e) => onChange(e)}/>
                :
                    <div style={{display: 'inline-block'}}>{value}</div>
                }
            </div>
            {onChange ? <button className="edit-save-button" onClick={() => setEditMode(!editMode)}>{editMode ? 'S' : 'E'}</button> : <></>}
        </div>
    )
}

const BookSelection = ({ num, book, setBook }) => {
    return (
        <div className="book-selection">
            <h3>{`Book #${num}`}</h3>
            {book ?
            <>
            <div>{`ISBN: ${book.isbn}`}</div>
            <div>{`Title: ${book.title}`}</div>
            <div>{`Author: ${book.author}`}</div>
            </>
            :
            <></>
            }
            <button>{book ? 'Edit selection': 'Make selection'}</button>
        </div>

    )
}
