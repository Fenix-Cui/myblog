import React from 'react';
import { Card } from '@alifd/next';
import { Chart, Geom } from 'bizcharts';
import mock from './mock.js';
import styles from './index.module.scss';
import axios from "axios";

class AllValue extends React.Component{
  updateValue = async () => {
    const res = await axios.get("http://localhost:9000/http/main/getAllVisits");
    this.setState({
      allValue: (res.data).length
    })
  }
  constructor() {
    super();
    this.state={
      allValue: 0
    }
    this.updateValue();
  }
  render() {
    return (
      <div className={styles.value}>{this.state.allValue}</div>
    );
  }
}

const DEFAULT_DATA = {
  subTitle: '总访问量',
  // value: mock.value,
  chartData: mock.saleList,
  des: '周同比:',
  rate: '-10.1',
  chartHeight: 100,
};

const FusionCardLineChart = (props) => {
  const { cardConfig = DEFAULT_DATA } = props;
  const { title, subTitle, value, chartData, des, rate, chartHeight } = cardConfig;
  return (
    <Card free>
      {title ? (
        <>
          <Card.Header title={title} />
          <Card.Divider />
        </>
      ) : null}
      <Card.Content>
        <div className={styles.subTitle}>{subTitle}</div>
        {/*<div className={styles.value}>{value}</div>*/}
        <AllValue></AllValue>
        <div className={styles.des}>
          {des}
          {/*判断rate是否增长*/}
          <span>{rate}{rate>0?'↑':'↓'}</span>
        </div>
        <Chart
          width={10}
          height={chartHeight}
          data={chartData}
          scale={{
            date: {
              range: [0, 1],
            },
          }}
          forceFit
          padding={['auto', '0']}
        >
          <Geom type="line" position="date*value" shape="smooth" color="#2B7FFB" />
          <Geom type="area" position="date*value" shape="smooth" color="#2B7FFB" opacity={0.1} />
          <Geom type="line" position="date*num" shape="smooth" color="#00D6CB" opacity={1} />
          <Geom type="area" position="date*num" shape="smooth" color="#00D6CB" opacity={0.1} />
        </Chart>
      </Card.Content>
    </Card>
  );
};

export default FusionCardLineChart;
