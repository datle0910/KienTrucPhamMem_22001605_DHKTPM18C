import { AppBar, Toolbar, Typography, Avatar } from "@mui/material";

export default function Header({ user }) {
  return (
    <AppBar position="static">
      <Toolbar style={{ display: "flex", justifyContent: "space-between" }}>
        <Typography variant="h6">🍔 Food Ordering</Typography>

        <div style={{ display: "flex", alignItems: "center", gap: 10 }}>
          <Typography>{user.username}</Typography>
          <Avatar>{user.username[0]}</Avatar>
        </div>
      </Toolbar>
    </AppBar>
  );
}