#include "textureLoader.h"
#include <stdio.h>
#include <GL/freeglut.h>
#include <GL/gl.h>
#include <iostream>

using namespace std;

textureLoader::textureLoader(const char *filename)
{
    textureLoader::f = fopen(filename, "rb");
    if (!textureLoader::f)
    {
        throw runtime_error("");
    }
    textureLoader::readBitmap();
}

textureLoader::~textureLoader()
{
    delete[] textureLoader::data;
    textureLoader::data = nullptr;
}

void textureLoader::readBitmap()
{
    unsigned char temp;
    fread(&bitmapfileheader, sizeof(BITMAPFILEHEADER), 1, textureLoader::f);
    if (textureLoader::bitmapfileheader.bfType != 0x4D42)
    {
        throw runtime_error ("");
    }
    fread(&bitmapinfoheader, sizeof(BITMAPINFOHEADER), 1, textureLoader::f);
    if (textureLoader::bitmapinfoheader.biSizeImage == 0)
    {
        textureLoader::bitmapinfoheader.biSizeImage = (textureLoader::bitmapinfoheader.biHeight * textureLoader::bitmapinfoheader.biWidth) * 3;
        textureLoader::imageSize = textureLoader::bitmapinfoheader.biSizeImage;
    }
    textureLoader::data = new unsigned char[textureLoader::imageSize];
    fseek(textureLoader::f, textureLoader::bitmapfileheader.bfOffBits, SEEK_SET);
    fread(textureLoader::data, 1, textureLoader::imageSize, textureLoader::f);
    for (int i = 0; i < (int) textureLoader::imageSize; i += 3)
    {
        temp = textureLoader::data[i];
        textureLoader::data[i] = textureLoader::data[i + 2];
        textureLoader::data[i + 2] = temp;
    }
    textureLoader::w = textureLoader::bitmapinfoheader.biWidth;
    textureLoader::h = textureLoader::bitmapinfoheader.biHeight;
    fclose(textureLoader::f);
}

GLuint textureLoader::getTexture()
{
    glGenTextures(1, &texture);
    glBindTexture(GL_TEXTURE_2D, texture);
    glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
    glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
    gluBuild2DMipmaps(GL_TEXTURE_2D, GL_RGB, textureLoader::w, textureLoader::h, GL_RGB, GL_UNSIGNED_BYTE, textureLoader::data);
    return texture;
}
