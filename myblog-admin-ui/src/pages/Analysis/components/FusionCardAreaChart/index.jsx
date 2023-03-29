import React from 'react';
import { Card } from '@alifd/next';
import { Chart, Geom } from 'bizcharts';
import mock from './mock.js';
import styles from './index.module.scss';
import axios from "axios";

class WeekValue extends React.Component{
  updateValue = async () => {
    const res = await axios.get("http://localhost:9000/http/main/getWeekVisits");
    this.setState({
      weekValue: (res.data).length
    })
  }
  constructor() {
    super();
    this.state={
      weekValue: 0
    }
    this.updateValue();
  }
  render() {
    return (
      <div className={styles.value}>{this.state.weekValue}</div>
    );
  }
}

const DEFAULT_DATA = {
  title: '',
  subTitle: '周访问量',
  // value: this.state.dayValue,
  chartData: mock.saleList,
  des: '周同比:',
  rate: '12.0',
  chartHeight: 100,
};

const FusionCardAreaChart = (props = DEFAULT_DATA) => {
  const { title, subTitle, value, chartData, des, rate, chartHeight } = { ...DEFAULT_DATA, ...props };
  return (
    <Card free className={styles.areaChart}>
      {title ? (
        <>
          <Card.Header title={title} />
          <Card.Divider />
        </>
      ) : null}
      <Card.Content>
        <div className={styles.subTitle}>{subTitle}</div>
        {/*<div className={styles.value}>{value}</div>*/}
        <WeekValue></WeekValue>
        <div className={styles.des}>
          {des}
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
          <Geom type="line" position="date*value" color="#00D6CB" shape="smooth" opacity={1} />
          <Geom type="area" position="date*value" color="#00D6CB" shape="smooth" opacity={0.1} />
        </Chart>
      </Card.Content>
    </Card>
  );
};

export default FusionCardAreaChart;
