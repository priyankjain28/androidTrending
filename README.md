# App Instruction
1. Download apk
2. MainActivity display trending android repositories - filter option you can select are best match, most stars, fewest stars, most forks, fewest forks, recently updated
3. MainActivity also display trending language repository like Kotlin, Java, Dart - filter options are Daily, Weekly, Monthly 
4. Detail Activity display detail information of selected repository
5. Web View Activity display web view of selected repository

# API used
Github
1. https://api.github.com/search/repositories?o=desc&q=android&s=stars
2. https://api.github.com/users/<username>

Free Source
1. https://github-trending-api.now.sh/repositories?language=<language>&since=weekly


# Code Architecture
Package  
1. activity - MainActivity, DetailActivity, WebviewActivity
2. adapter - ItemAdapter, LanguageAdapter
3. api - ApiClient, ApiInterface
4. fragment - AndroidFragment,TrendingFragment, FragmentTab
5. init - ApplicationInitiate
6. model - Item,ItemResponse,LanguageItem,Owner,UserProfile, License
7. utils - StarsComparator, ForksComparator, DateComparator, Method

# Concept used
1. Fragment
2. Recycler View, ViewPager, Card View
3. Retrofit
4. Webview
5. Java Concepts
6. Android UI
