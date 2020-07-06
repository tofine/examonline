import axiosServices from "./request"

const ReaderCenter = axiosServices("readercenter")

const ResourceCenter = axiosServices("resourcecenter")

export default {
  getUserInfoByOpenId: (data) => {
    return ReaderCenter("get", "wechatUser/getUserInfoByOpenId", data)
  },
  editUserInfo: (data) => {
    return ReaderCenter("post", "wechatUser/saveWechatUserExtendInfo", data)
  },
  getImages: (data) => {
    return ResourceCenter("get", "image/wallpaper", data)
  },
}