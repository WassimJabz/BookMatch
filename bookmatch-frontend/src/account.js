export function register(email, username, password){
    email = email.replace('@', '%40');
    fetch(`http://localhost:8080/registration?email=${email}&username=${username}&password=${password}`);
}

export function overrideBooks(book1, book2, book3){
    book1 = book1 ? `&book1=${serializeBook(book1)}` : null;
    book2 = book2 ? `&book2=${serializeBook(book2)}` : null;
    book3 = book3 ? `&book3=${serializeBook(book3)}` : null;

    let books = '';
    books += book1 ? book1 : '';
    books += book2 ? book2 : '';
    books += book3 ? book3 : '';
    books = books ? books.substring(1) : '';

    fetch(`http://localhost:8080/setBooks?${books}`)
}

function serializeBook(book){
    return `${book.isbn}%7C${book.title}%7C${serializeAuthors(book.authors)}%7C%${book.category}`;
}

function serializeAuthors(authors){
    return authors.join(',')
}

export function login(username, password){
    fetch(`http://localhost:8080/login?username=${username}&password=${password}`);
}