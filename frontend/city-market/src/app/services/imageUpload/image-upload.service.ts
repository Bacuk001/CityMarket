import {Injectable} from '@angular/core';
import {IImageUploadService} from "./iimage-upload.service";
import {Http} from "@angular/http";

const IMAGE_DOWNLOAD_SUCCESS: string = 'Изображение загруженно!';
const IMAGE_DO_NOT_DOWNLOAD: string = 'Не загруженно!';
const NAME_FORM_PROPERTY: string = 'file';
const URL: string = '/CityMarket/api/image';

/**
 * The class describes the basic principle of working with product images.
 */
@Injectable()
export class ImageUploadService implements IImageUploadService {
  constructor(private http: Http) {
  };

  /**
   * The method generates a request to load the application.
   */
  uploadImage(fileList: FileList) {
    let file: File = fileList[0];
    let formData: FormData = new FormData();
    formData.append(NAME_FORM_PROPERTY, file, file.name);
    return new Promise((response, reject) => {
      this.http.post(URL, formData)
        .subscribe(() => response(IMAGE_DOWNLOAD_SUCCESS),
          () => reject(IMAGE_DO_NOT_DOWNLOAD))
    });
  }
}
