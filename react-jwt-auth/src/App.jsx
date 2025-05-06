import React, { useState } from 'react';
import Login from './Login';
import Home from './Home';
import './App.css';

function App() {
  const [token, setToken] = useState(null);

  return (
    <div className="App">
      {!token ? (
        <Login setToken={setToken} />
      ) : (
        <Home token={token} />
      )}
    </div>
  );
}

export default App;
