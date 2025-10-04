#include "Labirint2D.h"
#include <bits/stdc++.h>
#include <GL/freeglut.h>
#include <GL/gl.h>
#include <iostream>

using namespace std;

Labirint2D *Labirint2D::labirint2D = nullptr;

Labirint2D::Labirint2D(int* argc, char *argv[])
{
    labirint = this;
    glutInit(&argc, argv);
    glutCreateWindow("Labirint with OpenGL");
    glutInitWindowSize(640, 480);
    glutDisplayFunc(displayCallBack);
    glutMainLoop();
}

Labirint2D::~Labirint2D()
{}

void Labirint::displayCallBack()
{
    labirint2D->output();
}

void Labirint2D::output()
{
    GLfloat coorX = 0.98f, coorY = 0.98f;
    glClearColor(0.8, 0.0, 0.0, 1.0);
    glClear(GL_COLOR_BUFFER_BIT);
    glPointSize(9);
    glBegin(GL_POINTS);
    for (int i = 0; i < getHeight(); i++)
    {
        for (int j = 0; j < getWidth(); j++)
        {
            switch (arr[i][j])
            {
            case '#' :
                glColor3f(0.0f, 0.0f, 0.0f);
                break;
            case '_' :
                glColor3f(1.0f, 1.0f, 1.0f);
                break;
            default :
                break;
            }
            glVertex2d(coorX, coorY);
            coorX += 0.1f;
        }
        coorX = 0.98f;
        coorY -= 0.1f;
    }
    glEnd();
    glFlush();
}
