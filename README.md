# Myshcn Family Backend (Sample)

This project uses `application.properties` for configuration. The repository includes `application.properties` that references environment variables so you don't commit secrets.

Quick start (Windows cmd.exe):

1. Set Supabase/Postgres connection (example using a full JDBC URL):

```
set JDBC_DATABASE_URL=jdbc:postgresql://db.yoursupabase.co:5432/postgres?sslmode=require
set SUPABASE_DB_USER=postgres
set SUPABASE_DB_PASSWORD=your_password_here
```

2. Run the app:

```
gradlew.bat bootRun
```

Endpoints:
- `GET /api/hello`
- Persons: `GET/POST/PUT/DELETE /api/persons`
- Relationships: `GET/POST/PUT/DELETE /api/relationships`
- Parent-child: `GET/POST/PUT/DELETE /api/parent-child`

Notes:
- Cloudinary config must be set via environment variables: `CLOUDINARY_CLOUD_NAME`, `CLOUDINARY_API_KEY`, `CLOUDINARY_API_SECRET`.
- If you prefer YAML, rename or delete `application.properties` and create `application.yml` with equivalent settings.
