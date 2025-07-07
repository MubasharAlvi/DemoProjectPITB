#include <jni.h>
#include <string>
#include <cstring>

/** Compile‑time FNV‑1a hash (32‑bit) */
constexpr uint32_t fnv1a(const char* s, uint32_t h = 2166136261u) {
    return (*s == '\0') ? h
                        : fnv1a(s + 1, (h ^ uint32_t(*s)) * 16777619u);
}

/** Helper macro so we can write H("BASE_URL") */
#define H(str) fnv1a(str)

/**  JNI bridge */

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_newprojectforhamza_data_remote_secretKey_SecretProvider_getSecretNative(
        JNIEnv*  env,
        jobject  /* this */,
        jstring  jKey
) {
    if (jKey == nullptr) return nullptr;

    const char* keyChars = env->GetStringUTFChars(jKey, nullptr);
    uint32_t    hash     = fnv1a(keyChars);

    const char* secret = "unknown_key";

    switch (hash) {
        /** ---------- BASE_URL ---------- */
        case H("BASE_URL"):
            if (strcmp(keyChars, "BASE_URL") == 0)
                secret = "https://api.themoviedb.org/3/";
            break;

            /** ---------- API_KEY ---------- */
        case H("API_KEY"):
            if (strcmp(keyChars, "API_KEY") == 0)
                secret = "15bfad0090cb7eec31022ab8ccf17dd3";
            break;

            /** ---------- URL_IMAGE ---------- */
        case H("URL_IMAGE"):
            if (strcmp(keyChars, "URL_IMAGE") == 0)
                secret = "https://image.tmdb.org/t/p/w500";
            break;

            /** ---------- URL_YOUTUBE ---------- */
        case H("URL_YouTUBE"):
            if (strcmp(keyChars, "URL_YouTUBE") == 0)
                secret = "https://www.youtube.com/watch?v=";
            break;

            /* ---------- ANALYTICS_KEY ---------- */
        case H("ANALYTICS_KEY"):
            if (strcmp(keyChars, "ANALYTICS_KEY") == 0)
                secret = "analytics_key_here";
            break;

            /** ---------- Another key (example) ---------- */
        case H("ANOTHER_KEY"):
            if (strcmp(keyChars, "ANOTHER_KEY") == 0)
                secret = "foo-bar-baz";
            break;

        default:
            /** fall through: unknown_key already set */
            break;
    }

    env->ReleaseStringUTFChars(jKey, keyChars);
    return env->NewStringUTF(secret);
}


