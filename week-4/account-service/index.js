require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const accountRoutes = require('./routes/accountRoutes');

const app = express();
const PORT = process.env.PORT || 3001;

// Middleware
app.use(express.json());

// Routes
app.use('/accounts', accountRoutes);

// Health check endpoint
app.get('/health', (req, res) => res.send('Account Service is running'));

// Database connection
const MONGODB_URI = process.env.MONGODB_URI || 'mongodb://localhost:27017/bank-account-db';
mongoose.connect(MONGODB_URI)
  .then(() => {
    console.log('Connected to MongoDB for Account Service');
    app.listen(PORT, () => {
      console.log(`Account Service listening on port ${PORT}`);
    });
  })
  .catch(err => {
    console.error('Failed to connect to MongoDB:', err);
  });
