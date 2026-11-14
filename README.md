# NeonLens X

ترجمة فورية لنصوص الشاشة على أندرويد: الألعاب، الروايات المصورة، المانغا، وأي تطبيق.
Real-time on-screen translation for Android: games, visual novels, manga and any app.

> **NeonLens X مبني على [PlayTranslate](https://github.com/dominostars/playtranslate)** ومرخّص مثله بـ GPL-3.0.
> ليس تطبيق PlayTranslate الرسمي، ولا علاقة رسمية له بمشروع PlayTranslate.
>
> NeonLens X is a fork of PlayTranslate under GPL-3.0. It is not the official PlayTranslate app and is not affiliated with or endorsed by the PlayTranslate project.

## ما الجديد في NeonLens X

- **ثيم نيون**: خلفية كحلية داكنة، بطاقات بحدود مضيئة من الوردي إلى الأزرق، عناوين سماوية، ترجمات بالأخضر الليموني. يمكن الرجوع للثيم الداكن أو الفاتح من المظهر.
- **نمط ترجمة مخصص** (الالتقاط والطبقة ← نمط ترجمة مخصص):
  - لون خلفية من اختيارك (أسود، كحلي، بنفسجي، رمادي، أبيض...) بدل اللون المأخوذ من اللعبة.
  - شفافية الخلفية من 30% إلى 100%.
  - لون النص (أبيض، أصفر، سماوي، أخضر ليموني، وردي، برتقالي، أسود).
  - خط عريض.
- **حجم خط أكبر**: الحد الأقصى لحجم خط الترجمة صار 32 بدل 20.
- اسم وأيقونة ومعرّف تطبيق جديد (`io.github.tara44x.neonlensx`)، فيمكن تثبيته بجانب PlayTranslate.

## الفروقات عن PlayTranslate

- خدمة PlayTranslate Cloud (خادم المشروع الأصلي) غير متاحة في هذه النسخة.
- روابط Discord والتبرع للمشروع الأصلي مخفية.
- «الإبلاغ عن خطأ» يشارك ملف السجل بدل إرساله لبريد المشروع الأصلي.
- فحص التحديثات يقرأ إصدارات هذا المستودع.
- حزم اللغات تُنزّل من نفس كتالوج PlayTranslate العام.

## البناء

كل رفع إلى `main` يبني APK تلقائياً عبر GitHub Actions: تبويب **Actions** ← آخر تشغيل ← **Artifacts** ← `NeonLensX-apk`.

البناء المحلي: Android Studio أو `./gradlew :app:assembleRelease` مع JDK 17 و Android SDK و NDK `28.2.13676358` (يُنزّل تلقائياً). المستودع يستخدم submodules، فاستنسخه بـ `git clone --recursive`.

## الترخيص

GPL-3.0، راجع `LICENSE`. الكود الأصلي حقوقه لمساهمي PlayTranslate. اسم PlayTranslate وشعاره علامات تجارية لمشروعه وفق `TRADEMARK.md`، ولا تستخدمها هذه النسخة إلا للإشارة إلى أنها مبنية عليه.

README المشروع الأصلي محفوظ في `upstream-docs/`.
