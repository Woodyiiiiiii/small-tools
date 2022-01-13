
1. lock
   1. db optimistic lock
   2. redis + lus lock 
      1. [What's the CAP](https://en.wikipedia.org/wiki/CAP_theorem) and [explanation](https://cloud.tencent.com/developer/article/1860632#:~:text=CAP%20%E7%90%86%E8%AE%BA%EF%BC%8C%E7%9B%B8%E4%BF%A1%E5%BE%88%E5%A4%9A%E4%BA%BA,%E9%A1%B9%E4%B8%AD%E7%9A%84%E4%B8%A4%E9%A1%B9%E3%80%82&text=%E5%A6%82%E6%9E%9C%E6%98%AF%E5%9C%A8%E8%81%8C%E5%9C%BA%E4%B8%8A,%E4%BE%9D%E6%8D%AECAP%20%E5%8E%BB%E5%90%A6%E5%86%B3%E5%AE%83%E3%80%82)
      2. [redis + lus](https://github.com/niceyoo/redis-setnx)
      3. [Distributed Lock](https://blog.csdn.net/jiandanokok/article/details/114296755?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164191129716780274135599%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=164191129716780274135599&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-114296755.first_rank_v2_pc_rank_v29&utm_term=%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81&spm=1018.2226.3001.4187)
   3. zookeeper lock
   4. redisson lock