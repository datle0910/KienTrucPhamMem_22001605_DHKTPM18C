import { useState } from "react";
import Login from "./pages/Login";
import Register from "./pages/Register";
import FoodList from "./pages/FoodList";
import Cart from "./pages/Cart";
import Payment from "./pages/Payment";
import AdminFood from "./pages/AdminFood";
import Header from "./components/Header";
import { Grid } from "@mui/material";

function App() {
  const [user, setUser] = useState(null);
  const [cart, setCart] = useState([]);
  const [order, setOrder] = useState(null);
  const [showRegister, setShowRegister] = useState(false);

  // chưa login
  if (!user) {
    return showRegister ? (
      <Register setUser={setUser} />
    ) : (
      <Login setUser={setUser} setShowRegister={setShowRegister} />
    );
  }

  // admin
  if (user.role === "ADMIN") {
    return <AdminFood />;
  }

  // payment
  if (order) {
    return <Payment order={order} />;
  }

  // user thường
  return (
    <>
      <Header user={user} />
      <Grid container spacing={2} sx={{ p: 2 }}>
        <Grid item xs={8}>
          <FoodList cart={cart} setCart={setCart} />
        </Grid>
        <Grid item xs={4}>
          <Cart cart={cart} setCart={setCart} user={user} setOrder={setOrder} />
        </Grid>
      </Grid>
    </>
  );
}

export default App;