/**
 * The interface describes the basic principle of working with product images.
 */
export interface IImageUploadService {
  /**
   * The method generates a request to load the application.
   */
  uploadImage(fileList: FileList);
}
