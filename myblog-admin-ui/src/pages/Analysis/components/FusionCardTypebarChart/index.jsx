import React from 'react';
import { Card } from '@alifd/next';
import { Chart, Geom, Coord } from 'bizcharts';
import mock from './mock.js';
import styles from './index.module.scss';
import axios from "axios";

class MonthValue extends React.Component{
  updateValue = async () => {
    const res = await axios.get("http://localhost:9000/http/main/getMonthVisits");
    this.setState({
      monthValue: (res.data).length
    })
  }
  constructor() {
    super();
    this.state={
      monthValue: 0
    }
    this.updateValue();
  }
  render() {
    return (
      <div className={styles.value}>{this.state.monthValue}</div>
    );
  }
}

const DEFAULT_DATA = {
  subTitle: '月访问量',
  // value: mock.value,
  chartData: mock.saleList,
  des: '周同比:',
  rate: '10.1',
  chartHeight: 100,
};

const FusionCardTypebarChart = (props) => {
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
        <MonthValue></MonthValue>
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
          padding={['auto', 'auto']}
        >
          <Coord transpose />
          <Geom type="interval" position="type*value" color={['type', ['#096DD9', '#209BFA']]} />
        </Chart>
      </Card.Content>
    </Card>
  );
};

export default FusionCardTypebarChart;
