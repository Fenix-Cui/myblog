import * as React from 'react';
import { runApp } from 'ice';
import LocaleProvider from '@/components/LocaleProvider';
import { getLocale } from '@/utils/locale';
// import {createBrowserHistory } from 'history';


const locale = getLocale();
const appConfig = {
  // history: createBrowserHistory(),
  app: {
    rootId: 'ice-container',
    addProvider: ({ children }) => <LocaleProvider locale={locale}>{children}</LocaleProvider>,
  },
};
runApp(appConfig);
