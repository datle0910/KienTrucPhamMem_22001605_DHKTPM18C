import axios from "axios";
import API from "../api/api";
import {
  Card,
  CardContent,
  Typography,
  Button,
  IconButton,
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";

export default function Cart({ cart, setCart, user, setOrder }) {
  const total = cart.reduce((sum, f) => sum + f.price, 0);

  const removeItem = (index) => {
    const newCart = [...cart];
    newCart.splice(index, 1);
    setCart(newCart);
  };

  const createOrder = async () => {
    const res = await axios.post(`${API.ORDER}/orders`, {
      userId: user.id,
      foodIds: cart.map((f) => f.id),
    });

    setOrder(res.data);
  };

  return (
    <Card>
      <CardContent>
        <Typography variant="h6">🛒 Cart</Typography>

        {cart.map((f, i) => (
          <div
            key={i}
            style={{
              display: "flex",
              justifyContent: "space-between",
              marginTop: 5,
            }}
          >
            <span>{f.name}</span>
            <IconButton onClick={() => removeItem(i)}>
              <DeleteIcon />
            </IconButton>
          </div>
        ))}

        <Typography sx={{ mt: 2 }}>Total: {total} VND</Typography>

        <Button
          fullWidth
          variant="contained"
          color="success"
          sx={{ mt: 1 }}
          onClick={createOrder}
        >
          Create Order
        </Button>
      </CardContent>
    </Card>
  );
}