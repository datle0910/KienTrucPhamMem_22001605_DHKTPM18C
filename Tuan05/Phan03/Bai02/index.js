const express = require('express');
const { MongoClient } = require('mongodb');

const app = express();
const PORT = 3000;

// MongoDB connection
const uri = 'mongodb://mongo:27017';
const client = new MongoClient(uri);

let db;

async function connectDB() {
  try {
    await client.connect();
    db = client.db('mydb');
    console.log('Connected to MongoDB');
  } catch (err) {
    console.error('MongoDB connection failed', err);
  }
}

connectDB();

// API test
app.get('/', async (req, res) => {
  try {
    const collections = await db.listCollections().toArray();
    res.send(`Collections: ${collections.map(c => c.name).join(', ')}`);
  } catch (err) {
    res.send('DB Error');
  }
});

app.listen(PORT, () => console.log(`Server running on port ${PORT}`));