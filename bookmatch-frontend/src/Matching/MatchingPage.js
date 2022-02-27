import { useState } from 'react';
import './MatchingPage.css';

export default function MatchingPage(){

    const [picUrl, setPicUrl] = useState('https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png');
    const [username, setUsername] = useState('wassim');
    const [email, setEmail] = useState('email@mail.com');
    const [about, setAbout] = useState('My name is Wassim and I LOVE books!')
    const [book1, setBook1] = useState({
        isbn: '2435341235412',
        title: 'The best book ever',
        authors: ['Philippe Sarouphim Hochar']
    });
    const [book2, setBook2] = useState(null);
    const [book3, setBook3] = useState(null);

    return (
        <>
            <div className="matching-container">
                <div className="card">
                    <div className="header">
                        <img src={picUrl} />
                        <h1>{username}</h1>
                    </div>
                    <section>
                        <h2>{`About ${username}`}</h2>
                        <p>{about}</p>
                    </section>
                    <section>
                        <h2>{`${username}'s favourites`}</h2>
                        <BookDisplay num={1} book={book1} />
                        <BookDisplay num={2} book={book2} />
                        <BookDisplay num={3} book={book3} />
                    </section>
                </div>
                <div className="choice">
                    <button>Decline</button>
                    <button>Accept</button>
                </div>
            </div>
        </>
    )
}

const BookDisplay = ({ num, book }) => {

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
        </div>

    )
}