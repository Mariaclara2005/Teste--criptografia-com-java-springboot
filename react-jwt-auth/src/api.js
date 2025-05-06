import axios from 'axios';

export async function login(username, password) {
  const response = await axios.post('http://localhost:8080/Login.jsx', {
    username,
    password
  });
  return response.data.token;
}
