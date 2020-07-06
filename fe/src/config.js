export const isDevelopment = process.env.NODE_ENV !== 'production';

const HOST_NAME = window.location.hostname || '';
const DOMAIN = HOST_NAME.slice(HOST_NAME.indexOf('.') + 1);
const PROTOCOL = window.location.protocol;

export const BASE_API_ROOT = window.location.origin + (isDevelopment ? '/api/' : '/');
export const COOKIE = 'userInfo';
export const cookiePath = { path: '/', maxAge: 60 * 60 * 24 * 7 };
export const protocol = PROTOCOL;
export const domain = DOMAIN;