This project now includes a global CORS filter and local H2 fallback so you can run the app without connecting to Supabase.

Key points
- CORS config reads properties from `application.properties`:
  - `app.cors.allowed-origins` (default: http://localhost:4200)
  - `app.cors.allowed-methods` (default: GET,POST,PUT,DELETE,OPTIONS)
  - `app.cors.allowed-headers` (default: *)
- To override set environment variable `CORS_ALLOWED_ORIGINS`.

Run locally (Windows cmd.exe)
1) Build: gradlew.bat clean build -x test
2) Run: gradlew.bat bootRun

If you see a CORS error like:
  "has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present"

Check two things:
- Client is sending the correct Origin (e.g., http://localhost:4200). Avoid accidental trailing characters such as `}` (the error you had contained `%7D`).
- The backend must include the Access-Control-Allow-Origin header. This project now registers a CorsFilter to set it.

Supabase notes
- When connecting to Supabase Postgres from a local machine, Supabase may restrict incoming client IPs. For local development this project will use an embedded H2 DB when `JDBC_DATABASE_URL` is not set.

Render deployment notes
- Use the provided Dockerfile (you asked; if not present, create one) or deploy as a Gradle app. Ensure the following env vars are set on Render:
  - JDBC_DATABASE_URL (your Supabase JDBC URL)
  - SUPABASE_DB_USER
  - SUPABASE_DB_PASSWORD
  - CORS_ALLOWED_ORIGINS (set to your frontend URL)


