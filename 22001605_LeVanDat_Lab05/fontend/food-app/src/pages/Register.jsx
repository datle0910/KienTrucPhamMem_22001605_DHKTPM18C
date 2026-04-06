import { useState } from "react";
import axios from "axios";
import API from "../api/api";
import { Card, CardContent, TextField, Button, Typography } from "@mui/material";

export default function Register({ setUser }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const register = async () => {
    try {
      await axios.post(`${API.USER}/users/register`, {
        username,
        password,
      });

      alert("Đăng ký thành công!");
    } catch {
      alert("Username đã tồn tại");
    }
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", marginTop: 100 }}>
      <Card sx={{ width: 300 }}>
        <CardContent>
          <Typography variant="h5">Register</Typography>

          <TextField fullWidth label="Username" sx={{ mt: 2 }} onChange={(e) => setUsername(e.target.value)} />
          <TextField fullWidth type="password" label="Password" sx={{ mt: 2 }} onChange={(e) => setPassword(e.target.value)} />

          <Button fullWidth variant="contained" sx={{ mt: 2 }} onClick={register}>
            Register
          </Button>
        </CardContent>
      </Card>
    </div>
  );
}