import React from 'react';

function Home({ token }) {
  return (
    <div className="home-container">
      <h1>Bem-vindo!</h1>
      <p>Seu token JWT é:</p>
      <pre>{token}</pre>
    </div>
  );
}

export default Home;
