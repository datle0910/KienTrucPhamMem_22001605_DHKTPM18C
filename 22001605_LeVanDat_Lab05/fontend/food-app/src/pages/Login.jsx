import { useState } from "react";
import axios from "axios";
import API from "../api/api";
import { Card, CardContent, TextField, Button, Typography } from "@mui/material";

export default function Login({ setUser, setShowRegister }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const login = async () => {
    const res = await axios.post(`${API.USER}/users/login`, {
      username,
      password,
    });

    setUser(res.data);
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", marginTop: 100 }}>
      <Card sx={{ width: 300 }}>
        <CardContent>
          <Typography variant="h5">Login</Typography>

          <TextField fullWidth label="Username" sx={{ mt: 2 }} onChange={(e) => setUsername(e.target.value)} />
          <TextField fullWidth type="password" label="Password" sx={{ mt: 2 }} onChange={(e) => setPassword(e.target.value)} />

          <Button fullWidth variant="contained" sx={{ mt: 2 }} onClick={login}>
            Login
          </Button>
          <Button onClick={() => setShowRegister(true)}>
            Chưa có tài khoản? Register
            </Button>
        </CardContent>
      </Card>
    </div>
  );
}