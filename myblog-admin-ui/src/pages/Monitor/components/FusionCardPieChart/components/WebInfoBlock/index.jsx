import React from 'react';
import { Box, Card } from '@alifd/next';
import styles from './index.module.scss';
import axios from "axios";

class BannedIP extends React.Component{
  BannedIPs = async () => {
    const res = await axios.get("http://localhost:9000/http/main/geBannedIPs");
    this.setState({
      bandedNums: (res.data).length
    })
  }
  constructor() {
    super();
    this.state={
      bandedNums: 0
    }
    this.BannedIPs();
  }
  render() {
    return (
      <div className={styles.value}>{this.state.bandedNums}</div>
    );
  }
}

const WebInfoBlock = (props) => {
  const { cardConfig } = props;
  const { value, name, des, rate, isRise } = cardConfig;
  return (
    <Card free className={styles.box}>
      <Card.Content
        style={{
          height: '100%',
        }}
      >
        <Box
          justify="center"
          align="center"
          style={{
            height: '100%',
          }}
        >
          <Box className={styles.content} justify="space-between" align="center">
            {/*<div className={styles.value}>{value}</div>*/}
            <BannedIP></BannedIP>
            <div className={styles.name}>{name}</div>
            <div className={styles.des}>
              {des}
              {isRise ? <span className={styles.rise}>{rate}↑</span> : <span className={styles.fall}>{rate}↓</span>}
            </div>
          </Box>
        </Box>
      </Card.Content>
    </Card>
  );
};

export default WebInfoBlock;
