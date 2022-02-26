import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import ProfilePage from './ProfilePage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="profile" element={<ProfilePage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
