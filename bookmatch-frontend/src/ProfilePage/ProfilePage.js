import { useEffect, useState } from "react"
import './ProfilePage.css'

export default function ProfilePage(){

    const [picUrl, setPicUrl] = useState('https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png');
    const [username, setUsername] = useState('username');
    const [email, setEmail] = useState('email@mail.com');
    const [about, setAbout] = useState('My name is Wassim and I LOVE books!')
    const [book1, setBook1] = useState({
        isbn: '2435341235412',
        title: 'The best book ever',
        authors: ['Philippe Sarouphim Hochar']
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
                <EditableInput isArea={false} title="Email:" value={email} />
                <EditableInput isArea={false} title="Username:" value={username} onChange={(e) => setUsername(e)}/>
                <EditableInput isArea={false} title="Profile pic url:" value={picUrl} onChange={(e) => setPicUrl(e)}/>
                <EditableInput isArea={true} title="About:" value={about} onChange={(e) => setAbout(e)}/>
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

const EditableInput = ({ isArea, title, value, onChange }) => {

    const [editMode, setEditMode] = useState(false);

    return (
        <div className="edit">
            <label>{title}</label>
            <div className="field">
                {editMode ?
                    isArea ?
                        <textarea value={value} onChange={(e) => onChange(e.target.value)} />
                        :
                        <input type="text" value={value} onChange={(e) => onChange(e.target.value)}/>
                    :
                    <div style={{display: 'inline-block'}}>{value}</div>
                }
                {onChange ? <button className="edit-save-button" onClick={() => setEditMode(!editMode)}>{editMode ? 'Save' : 'Edit'}</button> : <></>}
            </div>
        </div>
    )
}

const BookSelection = ({ num, book, setBook }) => {

    const [select, setSelect] = useState(false);

    return (
        <div className="book-selection">
            <h3>{`Book #${num}`}</h3>
            {book ?
            <>
            <div>{`ISBN: ${book.isbn}`}</div>
            <div>{`Title: ${book.title}`}</div>
            <div>{`Author: ${book.authors[0]}`}</div>
            <div>{`Category: ${book.category}`}</div>
            </>
            :
            <></>
            }
            <button onClick={() => setSelect(!select)}>{select ? 'Cancel selection' : book ? 'Edit selection': 'Make selection'}</button>
            {select ? <BookSelector setBook={(b) => { setBook(b); setSelect(false) }} /> : <></>}
        </div>

    )
}

const BookSelector = ({ setBook }) => {

    const [query, setQuery] = useState('');

    useEffect(() => {
        if(query.length <= 3) return;
        let q = query.replace(' ', '+');
        fetch(`https://www.googleapis.com/books/v1/volumes?q=${q}`).then(response => response.json().then(res =>{
            let filtered = res.items.filter(r => {
                return r && r.volumeInfo && r.volumeInfo.title && r.volumeInfo.authors && r.volumeInfo.industryIdentifiers && r.volumeInfo.categories;
            })
            let simplified = filtered.map(r => {
                return {
                    title: r.volumeInfo.title,
                    authors: r.volumeInfo.authors,
                    isbn: r.volumeInfo.industryIdentifiers[0].identifier,
                    category: r.volumeInfo.categories[0]
                }
            })
            setResults(() => simplified);
        }));
    }, [query]);

    const [results, setResults] = useState([]);

    return(
        <>
            <input type="text" placeholder="Start typing..." value={query} onChange={(e) => setQuery(e.target.value)} />
            <div className="results">
                {results.map(r =>
                    <div onClick={() => setBook(r)} key={r.isbn} className="line">
                        <div>{r.title}</div>
                        <div>{r.authors[0]}</div>
                        <div>{r.isbn}</div>
                        <div>{r.category}</div>
                    </div>
                )}
            </div>
        </>
    )

}