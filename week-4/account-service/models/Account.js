const mongoose = require('mongoose');

const accountSchema = new mongoose.Schema({
  customerName: {
    type: String,
    required: true
  },
  accountType: {
    type: String,
    enum: ['Checking', 'Savings'],
    required: true
  },
  balance: {
    type: Number,
    default: 0
  },
  createdAt: {
    type: Date,
    default: Date.now
  }
});

module.exports = mongoose.model('Account', accountSchema);
