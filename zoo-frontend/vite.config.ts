import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    host: '0.0.0.0',   // уже стоит, чтобы было доступно извне
    port: 5173,
    proxy: {
      // все запросы, начинающиеся с /api, будут перенаправляться на бэкенд:
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false,           // если https‑сервер с самоподписанным cert’ом
        rewrite: path => path.replace(/^\/api/, '/api')
      }
    }
  }
})
