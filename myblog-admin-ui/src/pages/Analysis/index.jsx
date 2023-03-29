import React from 'react';
import { ResponsiveGrid } from '@alifd/next';
import PageHeader from '@/components/PageHeader';
import FusionCardBarChart from './components/FusionCardBarChart';
import FusionCardAreaChart from './components/FusionCardAreaChart';
import FusionCardTypebarChart from './components/FusionCardTypebarChart';
import FusionCardLineChart from './components/FusionCardLineChart';
import FusionCardRankChart from './components/FusionCardRankChart';
import FusionCardPieChart from './components/FusionCardPieChart';
import FusionCardGroupBarChart from './components/FusionCardGroupBarChart';
import {render} from "react-dom";

const { Cell } = ResponsiveGrid;

const Analysis = () => {
  return (
    <ResponsiveGrid gap={20}>
      <Cell colSpan={12}>
        <PageHeader
          title="分析页面"
          breadcrumbs={[
            {
              name: 'Dashboard',
            },
            {
              name: '分析页面',
            },
          ]}
        />
      </Cell>

      <Cell colSpan={3}>
        {/*已修改，待传值*/}
        <FusionCardBarChart />
      </Cell>

      <Cell colSpan={3}>
        {/*已修改，待传值*/}
        <FusionCardAreaChart />
      </Cell>

      <Cell colSpan={3}>
        {/*已修改，待传值*/}
        <FusionCardTypebarChart />
      </Cell>

      <Cell colSpan={3}>
        {/*已修改，待传值*/}
        <FusionCardLineChart />
      </Cell>

      <Cell colSpan={12}>
        {/*已修改部分，待传值*/}
        <FusionCardRankChart />
      </Cell>

      <Cell colSpan={4}>
        {/*已修改，待传值*/}
        <FusionCardPieChart />
      </Cell>

      <Cell colSpan={8}>
        <FusionCardGroupBarChart />
      </Cell>
    </ResponsiveGrid>
  );
};

export default Analysis;
