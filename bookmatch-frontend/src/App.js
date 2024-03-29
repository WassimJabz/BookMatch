import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import ProfilePage from './ProfilePage/ProfilePage';
import LoginPage from './LoginPage/LoginPage';
import NavBar from "./NavBar/NavBar";
import ChattingPage from "./ChattingPage/ChattingPage";
import MatchingPage from "./Matching/MatchingPage";

function App() {
  return (
    <div>
      <NavBar />
      <div className="App">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<ChattingPage />} />
            <Route path="profile" element={<ProfilePage />} />
            <Route path="login" element={<LoginPage />} />
            <Route path="match" element={<MatchingPage />} />
          </Routes>
        </BrowserRouter>
      </div>
    </div>
  );
}

export default App;
