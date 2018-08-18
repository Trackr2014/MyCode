#python3
#抓取百度新闻
import requests
import os
import time
import re
from bs4 import BeautifulSoup
import random

COUNT = 0
HTMLTEXT = ""


# 数据保存
def save2Text(path, data):
    # 判断，若文件夹不存在则创建路径
    if not os.path.exists(path):
        os.makedirs(path)
    # 进入path文件夹下
    os.chdir(path)
    timeFileName = time.strftime('%Y%m%d', time.localtime())
    if not os.path.exists(timeFileName):
        os.makedirs(timeFileName)
    # 进入二级目录
    os.chdir(path + "\\" + timeFileName)
    file = open('data' + time.strftime('%H%M%S', time.localtime()) + '.txt',
                'w')
    file.write(data)
    file.close


# 数据请求
def requestData():
    requests.adapters.DEFAULT_RETRIES = 5
    session = requests.session()
    session.keep_alive = False
    requestStr = session.get("https://www.chinanews.com/")
    if requestStr.status_code == 200:
        # 获取编码格式
        # charCodingType = chardet.detect(requestStr.content)
        # coding = charCodingType["encoding"]
        # 打印解码后的页面数据
        print(requestStr.content.decode('utf-8', 'ignore'))
        resultData = getHousePriceInfo(
            requestStr.content.decode('utf-8', 'ignore'))
        save2Text("D:\py_data", resultData)


# 获取房价信息
def getHousePriceInfo(respondData):
    if respondData is not None:
        global HTMLTEXT
        soup = BeautifulSoup(respondData)
        keyWords = ("货币", "钱币", "楼市", "房价", "股票", "中国", "贸易战", "习近平")
        randomKey = keyWords[random.randint(0, 7)]
        aInfo = soup.find(string=re.compile(randomKey))
        if aInfo is not None:
            aUrlInfo = aInfo.find_parents("a")
            if len(aUrlInfo) is not None:
                for url in aUrlInfo:
                    if url.get('href') is not None:
                        HTMLTEXT = HTMLTEXT + str(url) + '\n'
                        requestData = requests.get(
                            url.get('href')).content.decode('utf-8', 'ignore')
                        # codingTpye = chardet.detect(requestData)
                        # coding = codingTpye["encoding"]
                        getHousePriceInfo(requestData)

    return HTMLTEXT


requestData()
