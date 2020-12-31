import React from 'react';
import { Card } from '@alifd/next';
import { Chart, Geom, Axis, Legend } from 'bizcharts';
import styles from './index.module.scss';

const DEFAULT_DATA = {
  title: '构建博客总消费',
  chartData: [
    {
      category: '一',
      value: 123,
      type: '域名',
    },
    {
      category: '一',
      value: 231,
      type: '服务器',
    },
    {
      category: '一',
      value: 321,
      type: '其他',
    },
    {
      category: '二',
      value: -234,
      type: '域名',
    },
    {
      category: '二',
      value: -342,
      type: '服务器',
    },
    {
      category: '二',
      value: -432,
      type: '其他',
    },
    {
      category: '三',
      value: 322,
      type: '域名',
    },
    {
      category: '三',
      value: 211,
      type: '服务器',
    },
    {
      category: '三',
      value: 113,
      type: '其他',
    },
    {
      category: '四',
      value: 435,
      type: '域名',
    },
    {
      category: '四',
      value: 543,
      type: '服务器',
    },
    {
      category: '四',
      value: 333,
      type: '其他',
    },
    {
      category: '五',
      value: 111,
      type: '域名',
    },
    {
      category: '五',
      value: 452,
      type: '服务器',
    },
    {
      category: '五',
      value: 234,
      type: '其他',
    },
  ],
  chartHeight: 500,
};

const FusionCardGroupBarChart = (props) => {
  const { cardConfig = DEFAULT_DATA } = props;
  const { title, chartData, chartHeight } = cardConfig;
  return (
    <Card free className={styles.FusionCardGroupBarChart}>
      <Card.Header title={title} />
      <Card.Divider />
      <Card.Content>
        <Chart renderer="canvas" forceFit width={10} height={chartHeight} data={chartData} padding={['80', 'auto']}>
          <Axis name="category" />
          <Axis name="value" />
          <Legend
            textStyle={{
              fill: '#666',
              fontSize: 14,
            }}
          />
          <Geom
            type="interval"
            position="category*value"
            color="type"
            adjust={[
              {
                type: 'dodge',
                marginRatio: 1 / 16,
              },
            ]}
          />
        </Chart>
      </Card.Content>
    </Card>
  );
};

export default FusionCardGroupBarChart;
